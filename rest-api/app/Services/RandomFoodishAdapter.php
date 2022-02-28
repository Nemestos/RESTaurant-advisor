<?php

namespace App\Services;

class RandomFoodishAdapter extends RandomFoodService
{

    public function __construct(private RandomFoodish $foodish)
    {
    }

    public function getRandomFood(): string
    {
        $resp_raw = $this->foodish->getRandomFoodish();
        $body = json_decode($resp_raw->getBody(), true);
        return $body["image"];
    }
}
