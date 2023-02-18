package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("88.201.168.197");
        System.out.println(geoIp);
    }
}