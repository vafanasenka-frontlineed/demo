package com.allot.cs.model.filter;

import lombok.*;


public class ServiceGatewayReport {

    @Getter
    @AllArgsConstructor
    public enum NetworkStatisticsByViewOption {

        SERVICE_GATEWAYS("Service Gateways"),
        LINE("Line"),
        PIPE("Pipe"),
        VC("VC"),
        ;

        private final String value;
    }

    @Getter
    @AllArgsConstructor
    public enum ResolutionViewOption {

        HOURLY("Hourly"),
        RAW("Raw"),
        DAILY("Daily"),
        ;

        private final String value;
    }

}
