package com.bookstore.service;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class GeoIpService {
    private final DatabaseReader dbReader;

    public GeoIpService() throws IOException {
        // Path to your GeoLite2-Country.mmdb file
        File database = new File("GeoLite2-Country.mmdb");
        this.dbReader = new DatabaseReader.Builder(database).build();
    }

    public String getCountryName(String ip) {
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse response = dbReader.country(ipAddress);
            if (response.getCountry() != null) {
                return response.getCountry().getName();
            }
        } catch (IOException | GeoIp2Exception e) {
            // log error or handle as needed
        }
        return null;
    }
}
