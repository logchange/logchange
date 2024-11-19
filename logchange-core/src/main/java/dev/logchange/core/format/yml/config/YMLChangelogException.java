package dev.logchange.core.format.yml.config;

import java.util.List;

public class YMLChangelogException extends RuntimeException {
   public YMLChangelogException(List<Exception> exceptions) {
       super(toString(exceptions));
   }

   private static String toString(List<Exception> exceptions) {
       StringBuilder sb = new StringBuilder();
       sb.append("Errors found:\n");
       exceptions.forEach(exception -> sb.append(exception.getMessage()).append("\n"));
       return sb.toString();
   }
}
