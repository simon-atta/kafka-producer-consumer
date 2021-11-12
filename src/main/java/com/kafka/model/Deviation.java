package com.kafka.model;

public class Deviation {

  private Integer exceptionQuantity;

  private String exceptionReason;

  private String reservationId;

  private ExceptionItem exceptionItem;

  public Integer getExceptionQuantity() {
    return exceptionQuantity;
  }

  public void setExceptionQuantity(Integer exceptionQuantity) {
    this.exceptionQuantity = exceptionQuantity;
  }

  public String getExceptionReason() {
    return exceptionReason;
  }

  public void setExceptionReason(String exceptionReason) {
    this.exceptionReason = exceptionReason;
  }

  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public ExceptionItem getExceptionItem() {
    return exceptionItem;
  }

  public void setExceptionItem(ExceptionItem exceptionItem) {
    this.exceptionItem = exceptionItem;
  }
}
