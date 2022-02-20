<?php

namespace Database\Factories;

use App\Providers\RestaurantNameProvider;
use Illuminate\Database\Eloquent\Factories\Factory;

class MenuFactory extends Factory
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
            "name" => sprintf("Menu %s%d", strtoupper($this->faker->randomLetter()), $this->faker->randomDigitNotNull()),
            "description" => sprintf("%d %s, %d %s, %d %s",
                $this->faker->randomDigitNotNull(), $this->faker->foodName(),
                $this->faker->randomDigitNotNull(), $this->faker->sauceName(),
                $this->faker->randomDigitNotNull(), $this->faker->beverageName()
            ),
            "price" => $this->faker->randomFloat(2, 1, 100),
        ];
    }
}
