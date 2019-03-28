package com.example.instantsapp.model.modelodados;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.squareup.moshi.Json;

@Entity(tableName = "becarios")
public class InfoUsuarios implements Parcelable {

    @PrimaryKey
    @NonNull
    private String id;
    @Json(name = "passwd")
    private String passwd;
    @Json(name = "name")
    private String name;
    @Json(name = "desc")
    private String desc;
    @Json(name = "picture")
    private String picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.passwd);
        dest.writeString(this.name);
        dest.writeString(this.desc);
        dest.writeString(this.picture);
    }

    public InfoUsuarios() {
    }

    protected InfoUsuarios(Parcel in) {
        this.id = in.readString();
        this.passwd = in.readString();
        this.name = in.readString();
        this.desc = in.readString();
        this.picture = in.readString();
    }

    public static final Parcelable.Creator<InfoUsuarios> CREATOR = new Parcelable.Creator<InfoUsuarios>() {
        @Override
        public InfoUsuarios createFromParcel(Parcel source) {
            return new InfoUsuarios(source);
        }

        @Override
        public InfoUsuarios[] newArray(int size) {
            return new InfoUsuarios[size];
        }
    };

    @Override
    public String toString() {
        return "InfoUsuarios{" +
                "id='" + id + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
