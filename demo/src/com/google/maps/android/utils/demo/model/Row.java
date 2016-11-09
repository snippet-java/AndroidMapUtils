package com.google.maps.android.utils.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

  @SerializedName("id") @Expose private String id;
  @SerializedName("key") @Expose private String key;
  @SerializedName("value") @Expose private Value value;
  @SerializedName("doc") @Expose private Doc doc;

  /**
   * @return The id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id The id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return The key
   */
  public String getKey() {
    return key;
  }

  /**
   * @param key The key
   */
  public void setKey(String key) {
    this.key = key;
  }

  /**
   * @return The value
   */
  public Value getValue() {
    return value;
  }

  /**
   * @param value The value
   */
  public void setValue(Value value) {
    this.value = value;
  }

  /**
   * @return The doc
   */
  public Doc getDoc() {
    return doc;
  }

  /**
   * @param doc The doc
   */
  public void setDoc(Doc doc) {
    this.doc = doc;
  }
}
