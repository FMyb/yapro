package org.example.dto;

public class ParticipantDto {
        private long id;
        private String name;
        private String wish;
        private Recipient recipient;

        public ParticipantDto(long id, String name, String wish, Recipient recipient) {
            this.id = id;
            this.name = name;
            this.wish = wish;
            this.recipient = recipient;
        }

        public ParticipantDto() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWish() {
            return wish;
        }

        public void setWish(String wish) {
            this.wish = wish;
        }

        public Recipient getRecipient() {
            return recipient;
        }

        public void setRecipient(Recipient recipient) {
            this.recipient = recipient;
        }

        public static class Recipient {
            private long id;
            private String name;
            private String wish;

            public Recipient(long id, String name, String wish) {
                this.id = id;
                this.name = name;
                this.wish = wish;
            }

            public Recipient() {
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getWish() {
                return wish;
            }

            public void setWish(String wish) {
                this.wish = wish;
            }
        }
    }