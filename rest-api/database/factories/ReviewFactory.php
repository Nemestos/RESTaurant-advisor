<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

class ReviewFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            "grade" => $this->faker->randomFloat(2, 0, 5),
            "description" => $this->faker->text(100)
        ];
    }
}
