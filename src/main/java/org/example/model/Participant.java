package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "participants")
public class Participant {
       @Id
       @GeneratedValue
       private long id;

       private String name;

       private String wish;

       @OneToOne
       private Participant recipient;

       @ManyToOne
       private Group group;

       public Participant(long id, String name, String wish, Participant recipient, Group group) {
              this.id = id;
              this.name = name;
              this.wish = wish;
              this.recipient = recipient;
       }

       public Participant() {
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

       public Participant getRecipient() {
              return recipient;
       }

       public void setRecipient(Participant recipient) {
              this.recipient = recipient;
       }

       public Group getGroup() {
              return group;
       }

       public void setGroup(Group group) {
              this.group = group;
       }
}
