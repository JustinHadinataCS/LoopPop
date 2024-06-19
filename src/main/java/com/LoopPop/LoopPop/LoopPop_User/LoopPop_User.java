package com.LoopPop.LoopPop.LoopPop_User;
import com.LoopPop.LoopPop.Comment.Comment;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table
public class LoopPop_User {
    @Id
    @SequenceGenerator(
            name = "mainUser_sequence",
            sequenceName = "mainUser_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "mainUser_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String hobby;
    private String favoriteMusic;

    @Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate dob;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @Transient
    private Integer age;

    public LoopPop_User(Long id,
                String name,
                String email,
                String hobby,
                String favoriteMusic,
                LocalDate dob) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.hobby = hobby;
        this.favoriteMusic = favoriteMusic;
        this.dob = dob;
    }

    public LoopPop_User(String name,
                String email,
                String hobby,
                String favoriteMusic,
                LocalDate dob) {
        this.name = name;
        this.email = email;
        this.hobby = hobby;
        this.favoriteMusic = favoriteMusic;
        this.dob = dob;
    }

    public LoopPop_User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getFavoriteMusic() {
        return favoriteMusic;
    }

    public void setFavoriteMusic(String favoriteMusic) {
        this.favoriteMusic = favoriteMusic;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "LoopPop_User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hobby='" + hobby + '\'' +
                ", favoriteMusic='" + favoriteMusic + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
//this is the getter, setter and constructor, basically simpan data spaya Layer lain bsa pake.