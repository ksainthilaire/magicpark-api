package com.magicpark.api.enums

enum class UserRank {
    // Guest who can make a quick purchase in anonymous mode without registering
    CUSTOMER,

    // Adivors handle support requests
    ADVISOR,

    // The supervisor supervises the activities
    SUPERVISOR,

    // Administrators have a right of access to all information and modification
    ADMINISTRATOR
}