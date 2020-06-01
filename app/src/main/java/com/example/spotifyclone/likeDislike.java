package com.example.spotifyclone;

public class likeDislike {



        public likeDislike(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;

        public likeDislike withId(String id) {
            this.id = id;
            return this;
        }


    }
