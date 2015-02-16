package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class Player {
    public static final String TAG = "Player";


    @Expose
    private String image;
    @Expose
    private String username;
    @Expose
    private Integer exp;
    @Expose
    private Integer level;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @Expose
    private Integer gender;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    //TODO: Generate getters and setters with Date objects.
    @Expose
    private String registered;
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("last_logout")
    @Expose
    private String lastLogout;
    @SerializedName("cl_player_id")
    @Expose
    private String clPlayerId;

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    public Player withImage(String image) {
        this.image = image;
        return this;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public Player withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     *
     * @return
     * The exp
     */
    public Integer getExp() {
        return exp;
    }

    /**
     *
     * @param exp
     * The exp
     */
    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Player withExp(Integer exp) {
        this.exp = exp;
        return this;
    }

    /**
     *
     * @return
     * The level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *
     * @param level
     * The level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    public Player withLevel(Integer level) {
        this.level = level;
        return this;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Player withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     *
     * @return
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Player withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     *
     * @return
     * The gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Player withGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    /**
     *
     * @return
     * The birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     *
     * @param birthDate
     * The birth_date
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Player withBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    /**
     *
     * @return
     * The registered
     */
    public String getRegistered() {
        return registered;
    }

    /**
     *
     * @param registered
     * The registered
     */
    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public Player withRegistered(String registered) {
        this.registered = registered;
        return this;
    }

    /**
     *
     * @return
     * The lastLogin
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     *
     * @param lastLogin
     * The last_login
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Player withLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    /**
     *
     * @return
     * The lastLogout
     */
    public String getLastLogout() {
        return lastLogout;
    }

    /**
     *
     * @param lastLogout
     * The last_logout
     */
    public void setLastLogout(String lastLogout) {
        this.lastLogout = lastLogout;
    }

    public Player withLastLogout(String lastLogout) {
        this.lastLogout = lastLogout;
        return this;
    }

    /**
     *
     * @return
     * The clPlayerId
     */
    public String getClPlayerId() {
        return clPlayerId;
    }

    /**
     *
     * @param clPlayerId
     * The cl_player_id
     */
    public void setClPlayerId(String clPlayerId) {
        this.clPlayerId = clPlayerId;
    }

    public Player withClPlayerId(String clPlayerId) {
        this.clPlayerId = clPlayerId;
        return this;
    }


}
