<?php

namespace Database\Seeders;

use App\Models\Menu;
use App\Models\Restaurant;
use Illuminate\Database\Seeder;

class RestaurantsSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Restaurant::truncate();
        $restaurant = Restaurant::factory()->count(15)->create();
        $restaurant->each(function (Restaurant $res) {
            $menus = Menu::factory()->count(3)->create(["restaurant_id" => $res->id]);
            $res->menus()->saveMany($menus);
        });
    }
}
