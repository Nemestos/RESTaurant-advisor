<?php

namespace App;

class Token
{
    const STRUCTURE = [
        'headers',
        'original' => [

            'access_token',
            'token_type'

        ],
        'exception'
    ];
    const USER_ABILITIES=["get_restaurants", "get_menus"];
    const ADMIN_ABILITIES=["*"];
}
