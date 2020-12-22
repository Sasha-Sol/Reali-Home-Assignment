package com.sasha.assignment.RealiEtlPipline.DAO

import com.sasha.assignment.RealiEtlPipline.model.Apartment
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
trait ApartmentDAO extends PagingAndSortingRepository[Apartment,Long]{

  def findByStatus (status: String, pageable: Pageable): Page[Apartment]

  //bonus
  def findByStatusInAndPriceIsBetweenAndBedroomsIsBetweenAndBathroomsIsBetween(status:java.util.List[String], pageable:Pageable, priceFrom:Int, priceTo:Int, bedroomsFrom:Int, bedroomsTo:Int, bathroomsFrom:Int, bathroomsTo:Int):Page[Apartment]

  def findByPriceIsBetweenAndBedroomsIsBetweenAndBathroomsIsBetween(pageable:Pageable, priceFrom:Int, priceTo:Int, bedroomsFrom:Int, bedroomsTo:Int, bathroomsFrom:Int, bathroomsTo:Int):Page[Apartment]

}
