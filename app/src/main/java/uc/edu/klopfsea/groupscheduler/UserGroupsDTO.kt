package uc.edu.klopfsea.groupscheduler

import java.time.LocalDateTime

data class UserGroupsDTO(var groupName: String = "", var groupPicture: Boolean = false, var dateTime: LocalDateTime = LocalDateTime.now(), var userGroupID: String = "", var groupDay: String = "") {
    override fun toString(): String {
        return "$groupDay $dateTime"
    }
}