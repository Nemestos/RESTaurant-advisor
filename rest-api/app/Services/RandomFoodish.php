<?php

namespace App\Services;

use Illuminate\Http\Client\Response;
use Illuminate\Support\Facades\Http;
use Psy\Util\Json;

class RandomFoodish
{
    public function getRandomFoodish(): Response
    {
        return Http::get("https://foodish-api.herokuapp.com/api/");
    }
}
