<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Str;

class UserFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array
     */
    public function definition()
    {
        return [
            'login'=>$this->faker->userName(),
            'password' => $this->faker->password(6), // password
            'email' => $this->faker->unique()->safeEmail(),
            'name' => $this->faker->name(),
            'firstname' => $this->faker->firstName(),
            'age' => $this->faker->numberBetween(1,100),


        ];
    }


}
