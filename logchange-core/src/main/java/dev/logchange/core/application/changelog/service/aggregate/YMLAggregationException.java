package dev.logchange.core.application.changelog.service.aggregate;

import java.util.List;

public class YMLAggregationException extends RuntimeException {
   public YMLAggregationException(List<Exception> exceptions) {
       super(toString(exceptions));
   }

   private static String toString(List<Exception> exceptions) {
       StringBuilder sb = new StringBuilder();
       sb.append("Errors found:\n");
       exceptions.forEach(exception -> sb.append(exception.getMessage()).append("\n"));
       return sb.toString();
   }
}
