package uc.edu.klopfsea.groupscheduler

import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDateTime

data class UserGroupsDTO(var groupName : String, var groupPicture : Boolean, var dateTime : LocalDateTime, var userGroupID : String = "", var groupDay : String = "") {
}