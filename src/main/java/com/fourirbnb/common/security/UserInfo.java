package com.fourirbnb.common.security;

import java.beans.ConstructorProperties;

public class UserInfo {
  private final Long userId;
//  private final Role Role;
  @ConstructorProperties({"userId"})
  public UserInfo(Long userId) {
    this.userId = userId;
  }
//  public UserInfo(Long userId, Role role) {
//    this.userId = userId;
//    Role = role;
//  }

  public Long getUserId() {return userId;}
//  public Role getRole() {return Role;}

  @Override
  public String toString() {
    return "UserInfo{userId=" + userId + "}";
  }
}
