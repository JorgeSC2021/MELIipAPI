package com.meli.ipAPI.DTO;

import java.util.List;

public class ipResponseServiceDTO {
    private String ip;
    private String type;
    private String continent_code;
    private String continent_name;
    private String country_code;
    private String country_name;
    private String region_code;
    private String region_name;
    private String city;
    private String zip;
    private double latitude;
    private double longitude;
    private Location location;
    private TimeZone time_zone;
    private Currency currency;
    private Connection connection;
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public TimeZone getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(TimeZone time_zone) {
        this.time_zone = time_zone;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public static class Location {
        private int geoname_id;
        private String capital;
        private List<Language> languages;
        private String country_flag;
        private String country_flag_emoji;
        private String country_flag_emoji_unicode;
        private String calling_code;
        private boolean is_eu;
        
        public int getGeoname_id() {
            return geoname_id;
        }

        public void setGeoname_id(int geoname_id) {
            this.geoname_id = geoname_id;
        }

        public String getCapital() {
            return capital;
        }

        public void setCapital(String capital) {
            this.capital = capital;
        }

        public List<Language> getLanguages() {
            return languages;
        }

        public void setLanguages(List<Language> languages) {
            this.languages = languages;
        }

        public String getCountry_flag() {
            return country_flag;
        }

        public void setCountry_flag(String country_flag) {
            this.country_flag = country_flag;
        }

        public String getCountry_flag_emoji() {
            return country_flag_emoji;
        }

        public void setCountry_flag_emoji(String country_flag_emoji) {
            this.country_flag_emoji = country_flag_emoji;
        }

        public String getCountry_flag_emoji_unicode() {
            return country_flag_emoji_unicode;
        }

        public void setCountry_flag_emoji_unicode(String country_flag_emoji_unicode) {
            this.country_flag_emoji_unicode = country_flag_emoji_unicode;
        }

        public String getCalling_code() {
            return calling_code;
        }

        public void setCalling_code(String calling_code) {
            this.calling_code = calling_code;
        }

        public boolean isIs_eu() {
            return is_eu;
        }

        public void setIs_eu(boolean is_eu) {
            this.is_eu = is_eu;
        }

        public static class Language {
            private String code;
            private String name;
            private String nativeName;
            
            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNativeName() {
                return nativeName;
            }

            public void setNativeName(String nativeName) {
                this.nativeName = nativeName;
            }
        }
    }

    public static class TimeZone {
        private String id;
        private String current_time;
        private int gmt_offset;
        private String code;
        private boolean is_daylight_saving;

        // Getters y Setters
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCurrent_time() {
            return current_time;
        }

        public void setCurrent_time(String current_time) {
            this.current_time = current_time;
        }

        public int getGmt_offset() {
            return gmt_offset;
        }

        public void setGmt_offset(int gmt_offset) {
            this.gmt_offset = gmt_offset;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isIs_daylight_saving() {
            return is_daylight_saving;
        }

        public void setIs_daylight_saving(boolean is_daylight_saving) {
            this.is_daylight_saving = is_daylight_saving;
        }
    }

    public static class Currency {
        private String code;
        private String name;
        private String plural;
        private String symbol;
        private String symbol_native;

        // Getters y Setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlural() {
            return plural;
        }

        public void setPlural(String plural) {
            this.plural = plural;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol_native() {
            return symbol_native;
        }

        public void setSymbol_native(String symbol_native) {
            this.symbol_native = symbol_native;
        }
    }

    public static class Connection {
        private int asn;
        private String isp;

        // Getters y Setters
        public int getAsn() {
            return asn;
        }

        public void setAsn(int asn) {
            this.asn = asn;
        }

        public String getIsp() {
            return isp;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }
    }
}