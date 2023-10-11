package it.euris.teslabatteryBm.utility;

import it.euris.teslabatteryBm.enums.StatusCicloProduttivo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DataConversionUnit {

  private DataConversionUnit() {
  }

  public static String numberToString(Number value) {
    return value == null ? null : value.toString();
  }

  public static Long stringToLong(String value) {
    return value == null ? null : Long.parseLong(value);
  }

  public static Integer stringToInteger(String value) {
    return value == null ? null : Integer.parseInt(value);
  }

  public static Double stringToDouble(String value) {
    return value == null ? null : Double.parseDouble(value);
  }

  public static LocalDateTime stringToLocalDateTime(String value) {
    return value == null ? null : LocalDateTime.parse(value);
  }

  public static String localDateTimeToString(LocalDateTime value) {
    return value == null ? null : value.toString();
  }

  public static LocalTime stringToLocalTime(String value) {
    return value == null ? null : LocalTime.parse(value);
  }

  public static String localTimeToString(LocalTime value) {
    return value == null ? null : value.toString();
  }

  public static StatusCicloProduttivo stringToStatus(String value) {
    for (StatusCicloProduttivo statusCicloProduttivo : StatusCicloProduttivo.values()) {
      if (statusCicloProduttivo.name().equalsIgnoreCase(value))
        return statusCicloProduttivo;
    }
    return null;
  }

  public static String statusToString(StatusCicloProduttivo value) {
    return value == null ? null : value.name();
  }

  public static String booleanToString(Boolean bool) {
    return bool ? "true" : "false";
  }

  public static Boolean stringToBoolean(String value) {
    return value == null ? null : Boolean.valueOf(value);
  }

}
