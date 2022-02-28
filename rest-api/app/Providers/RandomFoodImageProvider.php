<?php

namespace App\Providers;

use App\Services\RandomFoodish;
use App\Services\RandomFoodishAdapter;
use App\Services\RandomFoodService;
use Illuminate\Support\ServiceProvider;

class RandomFoodImageProvider extends ServiceProvider
{
    /**
     * Register services.
     *
     * @return void
     */
    public function register()
    {

    }

    /**
     * Bootstrap services.
     *
     * @return void
     */
    public function boot()
    {
        $this->app->bind(RandomFoodService::class, function ($app) {
            return new RandomFoodishAdapter(new RandomFoodish());
        });
    }
}
