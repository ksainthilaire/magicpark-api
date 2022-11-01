package com.magicpark.api.enums

enum class UserRank {
    // Administrators have a right of access to all information and modification
    ADMINISTRATOR,

    // The supervisor supervises the activities
    SUPERVISOR,

    // Adivors handle support requests
    ADVISOR,

    // Guest who can make a quick purchase in anonymous mode without registering
    CUSTOMER
}