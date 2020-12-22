package com.sasha.assignment.RealiEtlPipline.controller

import com.sasha.assignment.RealiEtlPipline.model.{Apartment, ApartmentRequest}
import com.sasha.assignment.RealiEtlPipline.services.RealiService
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RequestMapping, RequestParam, RestController}


@RestController
@RequestMapping(Array("/reali-api"))
class RealiController(realiService: RealiService) {


  @GetMapping(value = Array("/start"))
  def dataExtraction(): Unit = {
    realiService.extract
  }

  @GetMapping(value = Array("/listings"))
  def getApartments(@RequestParam(value = "page", defaultValue = "0") page: Int,
                    @RequestParam(value = "pageSize", defaultValue = "100") pageSize: Int,
                    @RequestParam(value = "status", defaultValue = "") status: String): java.util.List[Apartment] =

    realiService.findByStatus(status, PageRequest.of(page, pageSize))

  @PostMapping(value = Array("/listings/bonus"),  produces = Array("application/json"))
  def getApartmentWithFilter(@RequestBody requestBody: ApartmentRequest) = {

    realiService.findBy(requestBody)
  }

}
