package uc.edu.klopfsea.groupscheduler

import java.sql.Timestamp

data class UserGroupsDTO
(var groupName : String, var groupPicture : Boolean, var dateTime : Timestamp, var userGroupID : String = "") {
}