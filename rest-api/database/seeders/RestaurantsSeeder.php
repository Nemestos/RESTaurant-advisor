<?php

namespace Database\Seeders;

use App\Models\Menu;
use App\Models\Restaurant;
use App\Providers\RandomFoodImageProvider;
use App\Services\RandomFoodService;
use Illuminate\Database\Seeder;

class RestaurantsSeeder extends Seeder
{

    private RandomFoodService $randomFoodService;

    public function __construct(RandomFoodService $randomFoodService)
    {
        $this->randomFoodService = $randomFoodService;
    }

    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Restaurant::truncate();
        $restaurant = Restaurant::factory()->count(10)->create();
        $restaurant->each(function (Restaurant $item){
            $item->update(["image_url" => $this->randomFoodService->getRandomFood()]);
        });
        $restaurant->each(function (Restaurant $res) {
            $menus = Menu::factory()->count(3)->create(["restaurant_id" => $res->id]);
            $res->menus()->saveMany($menus);
        });
    }
}
