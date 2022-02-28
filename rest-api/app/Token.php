<?php

namespace App;

class Token
{
    const STRUCTURE = [
        'token',

    ];
    const USER_ABILITIES = ["get_restaurants", "get_menus"];
    const ADMIN_ABILITIES = [
        "get_restaurants",
        "get_menus",
        "get_users",
        "create_restaurant",
        "create_menu",
        "put_restaurant",
        "put_menu",
        "delete_restaurant",
        "delete_menu"
    ];
}
