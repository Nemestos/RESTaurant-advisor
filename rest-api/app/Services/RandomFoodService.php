<?php

namespace App\Services;

abstract class RandomFoodService
{

    public abstract function getRandomFood(): string;
}

