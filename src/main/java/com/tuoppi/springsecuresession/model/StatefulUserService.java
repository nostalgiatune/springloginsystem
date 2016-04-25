/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuoppi.springsecuresession.model;

import com.tuoppi.springsecuresession.user.UserProfile;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Tuoppi
 */
public interface StatefulUserService extends Serializable {
    String getActiveUsername();
    String getActiveUserPersonalData();
    List<UserProfile> getUserProfiles();
}
