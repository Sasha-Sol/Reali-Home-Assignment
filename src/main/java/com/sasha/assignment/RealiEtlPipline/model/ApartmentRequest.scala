package com.sasha.assignment.RealiEtlPipline.model

import java.util

case class ApartmentRequest  (
                               var status: util.ArrayList[String],
                               var page: Int,
                               var pageSize: Int,
                               var priceFrom: Int,
                               var priceTo: Int,
                               var bedroomsFrom: Int,
                               var bedroomsTo: Int,
                               var bathroomsFrom: Int,
                               var bathroomsTo: Int
                           ) {


  if (pageSize==0) pageSize=100
  if (priceTo==0) priceTo=99999999
  if (bedroomsTo == 0) bedroomsTo=999
  if (bathroomsTo == 0) bedroomsTo=999

}
