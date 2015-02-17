package com.playbasis.android.playbasissdk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playbasis.android.playbasissdk.core.SDKUtil;
import com.playbasis.android.playbasissdk.helper.DateHelper;
import com.playbasis.android.playbasissdk.helper.Validator;
import com.playbasis.android.playbasissdk.http.toolbox.HttpModel;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by gregoire barret on 2/16/15.
 * For PlayBasisSdk project.
 */
public class Player implements HttpModel{
    public static final String TAG = "Player";


    @Expose
    private String image;
    @Expose
    private String email;
    @Expose
    private String username;
    @Expose
    private Integer exp;
    @Expose
    private Integer level;
    @Expose
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @Expose
    private Gender gender;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @Expose
    private String registered;
    @SerializedName("percent_of_level")
    @Expose
    private Float percentOfLevel;
    @SerializedName("level_title")
    @Expose
    private String levelTitle;
    @SerializedName("level_image")
    @Expose
    private String levelImage;
    @Expose
    private List<Badge> badges = new ArrayList<>();
    @Expose
    private List<Point> points = new ArrayList<>();
    @SerializedName("last_login")
    @Expose
    private String lastLogin;
    @SerializedName("last_logout")
    @Expose
    private String lastLogout;
    @SerializedName("cl_player_id")
    @Expose
    private String clPlayerId;
    @SerializedName("facebook_id")
    @Expose
    protected String facebookId;
    @SerializedName("twitter_id")
    @Expose
    protected String twitterId;
    @Expose
    protected String password;


    public Player() {
    }

    /**
     * Constructor with requires attributes.
     * @param email Require.
     * @param username Require.
     * @param clPlayerId Require.
     */
    public Player(String email, String username, String clPlayerId) {
        this.email = email;
        this.username = username;
        this.clPlayerId = clPlayerId;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        if(!Validator.isValid(image))return SDKUtil.DEFAULT_IMAGE_URL;
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
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public Player withEmail(String email) {
        this.email = email;
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
     * The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     * The phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Player withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    public Gender getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Player withGender(Gender gender) {
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
     * The registered
     */
    public Date getDateRegistered() {
        return DateHelper.stringToDate(registered);
    }

    /**
     *
     * @param registered
     * The registered
     */
    public void setRegistered(Date registered) {
        this.registered = DateHelper.dateToString(registered);
    }

    public Player withRegistered(Date registered) {
        this.registered = DateHelper.dateToString(registered);
        return this;
    }


    /**
     *
     * @return
     *     The percentOfLevel
     */
    public Float getPercentOfLevel() {
        return percentOfLevel;
    }

    /**
     *
     * @param percentOfLevel
     *     The percent_of_level
     */
    public void setPercentOfLevel(Float percentOfLevel) {
        this.percentOfLevel = percentOfLevel;
    }

    /**
     *
     * @return
     *     The levelTitle
     */
    public String getLevelTitle() {
        return levelTitle;
    }

    /**
     *
     * @param levelTitle
     *     The level_title
     */
    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    /**
     *
     * @return
     *     The levelImage
     */
    public String getLevelImage() {
        return levelImage;
    }

    /**
     *
     * @param levelImage
     *     The level_image
     */
    public void setLevelImage(String levelImage) {
        this.levelImage = levelImage;
    }

    /**
     *
     * @return
     *     The badges
     */
    public List<Badge> getBadges() {
        return badges;
    }

    /**
     *
     * @param badges
     *     The badges
     */
    public void setBadges(List<Badge> badges) {
        this.badges = badges;
    }

    /**
     *
     * @return
     *     The points
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     *
     * @param points
     *     The points
     */
    public void setPoints(List<Point> points) {
        this.points = points;
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
     * The lastLogin
     */
    public Date getDateLastLogin() {
        return DateHelper.stringToDate(lastLogin);
    }

    /**
     *
     * @param lastLogin
     * The last_login
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = DateHelper.dateToString(lastLogin);
    }

    public Player withLastLogin(Date lastLogin) {
        this.lastLogin = DateHelper.dateToString(lastLogin);
        return this;
    }

    /**
     *
     * @return
     * The lastLogout
     */
    public Date getDateLastLogout() {
        return DateHelper.stringToDate(lastLogout);
    }

    /**
     *
     * @param lastLogout
     * The last_logout
     */
    public void setLastLogout(Date lastLogout) {
        this.lastLogout = DateHelper.dateToString(lastLogout);
    }

    public Player withLastLogout(Date lastLogout) {
        this.lastLogout = DateHelper.dateToString(lastLogout);
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


    /**
     *
     * @return
     * The facebook id
     */
    public String getFacebookId() {
        return facebookId;
    }

    /**
     *
     * @param facebookId
     * The facebook id
     */
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Player withFacebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;
    }


    /**
     *
     * @return
     * The twitterId
     */
    public String getTwitterId() {
        return twitterId;
    }

    /**
     *
     * @param twitterId
     * The twitterId
     */
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    public Player withTwitterId(String twitterId) {
        this.twitterId = twitterId;
        return this;
    }


    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Player withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "Player{" +
                "image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", exp=" + exp +
                ", level=" + level +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthDate='" + birthDate + '\'' +
                ", registered='" + registered + '\'' +
                ", percentOfLevel=" + percentOfLevel +
                ", levelTitle='" + levelTitle + '\'' +
                ", levelImage='" + levelImage + '\'' +
                ", badges=" + badges +
                ", points=" + points +
                ", lastLogin='" + lastLogin + '\'' +
                ", lastLogout='" + lastLogout + '\'' +
                ", clPlayerId='" + clPlayerId + '\'' +
                '}';
    }

    @Override
    public Boolean isValid(){
        return Validator.isValidAlphaNum(clPlayerId) && Validator.isValid(username) &&  Validator.isValidEmail(email);
    }

    @Override
    public List<NameValuePair> toParams() {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("id", clPlayerId));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("image", getImage()));
        if(Validator.isValid(phoneNumber))  params.add(new BasicNameValuePair("phone_number", phoneNumber));
        if(Validator.isValid(facebookId))   params.add(new BasicNameValuePair("facebook_id", facebookId));
        if(Validator.isValid(twitterId))    params.add(new BasicNameValuePair("twitter_id", twitterId));
        if(Validator.isValid(password))     params.add(new BasicNameValuePair("password", password));
        if(Validator.isValid(firstName))    params.add(new BasicNameValuePair("first_name", firstName));
        if(Validator.isValid(lastName))     params.add(new BasicNameValuePair("last_name", lastName));
        if(Validator.isValid(gender))       params.add(new BasicNameValuePair("gender", String.valueOf(gender.getGender())));
        if(Validator.isValid(birthDate))    params.add(new BasicNameValuePair("birth_date", birthDate));
        
        return params;
    }


}
