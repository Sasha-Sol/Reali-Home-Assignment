package com.sasha.assignment.RealiEtlPipline.model

import javax.persistence.{Entity, GeneratedValue, GenerationType, Id, Table}

import scala.annotation.meta.field
import scala.beans.BeanProperty

@Entity
@Table(name = "Apartment")
case class Apartment(
                      @(Id @field) @GeneratedValue(strategy = GenerationType.AUTO) @BeanProperty var id: Long,
                      @BeanProperty var street: String,
                      @BeanProperty var status: String,
                      @BeanProperty var price: Int,
                      @BeanProperty var bedrooms: Int,
                      @BeanProperty var bathrooms: Int,
                      @BeanProperty var square_feet: Int,
                      @BeanProperty var latitude: Float,
                      @BeanProperty var longitude: Float

                    ) {

  def this() = this(0, null, null, 0, 0, 0, 0, 0, 0)
}





