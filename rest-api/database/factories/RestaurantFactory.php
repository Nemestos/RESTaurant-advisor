<?php

namespace Database\Factories;

use App\Providers\RandomFoodImageProvider;
use App\Providers\RestaurantNameProvider;
use App\Services\RandomFoodService;
use Illuminate\Database\Eloquent\Factories\Factory;

class RestaurantFactory extends Factory
{

    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        $this->faker->addProvider(new RestaurantNameProvider($this->faker));
        return [
            "name" => $this->faker->unique()->restaurantName(),
            "description" => $this->faker->sentence(10),
            "grade" => $this->faker->randomFloat(1, 0, 5),
            "localization" => $this->faker->address(),
            "phone_number" => $this->faker->e164PhoneNumber(),
            "website" => $this->faker->url(),
            "hours" => $this->faker->time("l-l H-H")
        ];
    }
}
