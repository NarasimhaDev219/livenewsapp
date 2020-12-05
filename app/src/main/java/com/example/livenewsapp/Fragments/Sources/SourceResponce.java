package com.example.livenewsapp.Fragments.Sources;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder({
        "status",
        "sources"
})
public class SourceResponce implements Serializable {

    @JsonProperty("status")
    private String status = null;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("sources")
    private List<SourceData> data = null;

    @JsonProperty("sources")
    public List<SourceData> getData() {
        return data;
    }
    @JsonProperty("sources")
    public void setData(List<SourceData> data) {
        this.data = data;
    }

    public static class SourceData {
        @JsonPropertyOrder({
                "id",
                "name",
                "description",
                "url",
                "category",
                "language",
                "country",

        })
        @JsonProperty("id")
        private String id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("description")
        private String description;
        @JsonProperty("url")
        private String url;
        @JsonProperty("category")
        private String category;
        @JsonProperty("language")
        private String language;
        @JsonProperty("country")
        private String country;

        @JsonProperty("id")
        public String getId() {
            return id;
        }
        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("name")
        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty("description")
        public String getDescription() {
            return description;
        }
        @JsonProperty("description")
        public void setDescription(String description) {
            this.description = description;
        }

        @JsonProperty("url")
        public String getUrl() {
            return url;
        }

        @JsonProperty("url")
        public void setUrl(String url) {
            this.url = url;
        }

        @JsonProperty("category")
        public String getCategory() {
            return category;
        }

        @JsonProperty("category")
        public void setCategory(String category) {
            this.category = category;
        }

        @JsonProperty("language")
        public String getLanguage() {
            return language;
        }

        @JsonProperty("language")
        public void setLanguage(String language) {
            this.language = language;
        }

        @JsonProperty("country")
        public String getCountry() {
            return country;
        }

        @JsonProperty("country")
        public void setCountry(String country) {
            this.country = country;
        }
    }
}
