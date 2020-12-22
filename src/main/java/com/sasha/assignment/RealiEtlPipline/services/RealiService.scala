package com.sasha.assignment.RealiEtlPipline.services

import java.util

import com.sasha.assignment.RealiEtlPipline.DAO.ApartmentDAO
import com.sasha.assignment.RealiEtlPipline.model.{Apartment, ApartmentRequest}
import org.springframework.data.domain.{Page, PageRequest, Pageable}
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class RealiService(apartmentDAO: ApartmentDAO) {
  val LISTING_DETAILS_URL: String = "https://server-assignment.s3.amazonaws.com/listing-details.csv"

  @Transactional
  def extract = {
    val extractedCsv = extractCsv(LISTING_DETAILS_URL)
    createAndSave(extractedCsv)
  }


  def extractCsv(url: String): List[String] = {
    val src = scala.io.Source.fromURL(url)
    val result = src.getLines.toList
    src.close()
    result
  }

  def createAndSave(dataList: List[String]): Unit = {
    val apartments = new util.ArrayList[Apartment]()

    dataList.tail.map {
      case s"$id,$street,$status,$price,$bedrooms,$bathrooms,$sq_ft,$lat,$lng" =>
        apartments.add(Apartment(id.toLong, street, status, price.toInt, bedrooms.toInt, bathrooms.toInt, sq_ft.toInt, lat.toFloat, lng.toFloat))

      case line => throw new RuntimeException(s"Error accured while exctracting lising-details.csv $line")
    }
    apartmentDAO.saveAll(apartments)
  }

  def findByStatus(status: String, pageable: Pageable) = {
    apartmentDAO.findByStatus(status, pageable).getContent
  }

  def findBy(requestBody: ApartmentRequest):Page[Apartment] = {
    val status = requestBody.status
    if( status == null)
      apartmentDAO.findByPriceIsBetweenAndBedroomsIsBetweenAndBathroomsIsBetween(
        pageable = PageRequest.of(requestBody.page, requestBody.pageSize),
        priceFrom = requestBody.priceFrom,
        priceTo = requestBody.priceTo,
        bedroomsFrom = requestBody.bedroomsFrom,
        bedroomsTo = requestBody.bedroomsTo,
        bathroomsFrom = requestBody.bathroomsFrom,
        bathroomsTo = requestBody.bathroomsTo)
    else
      apartmentDAO.findByStatusInAndPriceIsBetweenAndBedroomsIsBetweenAndBathroomsIsBetween(
        status = requestBody.status,
        pageable = PageRequest.of(requestBody.page, requestBody.pageSize),
        priceFrom = requestBody.priceFrom,
        priceTo = requestBody.priceTo,
        bedroomsFrom = requestBody.bedroomsFrom,
        bedroomsTo = requestBody.bedroomsTo,
        bathroomsFrom = requestBody.bathroomsFrom,
        bathroomsTo = requestBody.bathroomsTo)
  }
  
}

