package org.thoughtworks.lean.identity.domain;

public class Authentication {

  private String name;

  private String credentials;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCredentials() {
    return credentials;
  }

  public void setCredentials(String credentials) {
    this.credentials = credentials;
  }

  @Override
  public String toString() {
    return "Authentication Object {" +
      "name='" + name.replaceFirst("@.*", "@***") +
      ", credentials='" + credentials.substring(0, 10) +
      '}';
  }
}
