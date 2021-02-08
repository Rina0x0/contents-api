import com.myapp.contentsapi.domain.*
import com.myapp.contentsapi.driver.db.UserInfo

class UserInfoDomainCreator(val usersInfo: List<UserInfo>) {
    companion object {
        fun of(usersInfo: List<UserInfo>): UserInfoDomainCreator {
            return UserInfoDomainCreator(usersInfo)
        }
    }
    fun toUsers(): Users {
        return usersInfo.map {
            User(
                Id(it.id),
                FirstName(it.firstName),
                LastName(it.lastName),
                Department(it.department),
                null
            )
        }.let(::Users)
    }
}