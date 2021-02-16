package uc.edu.klopfsea.groupscheduler

import java.sql.Date
import java.sql.Timestamp

data class UserGroupsDTO(var groupName : String, var groupPicture : Boolean, var dateTime : java.util.Date, var userGroupID : String = "", var groupDay : String = "") {
}