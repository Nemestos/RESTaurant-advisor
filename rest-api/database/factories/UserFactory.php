<?php

namespace Database\Factories;

use App\Models\User;
use App\Token;
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
            'login' => $this->faker->unique()->userName(),
            'password' => $this->faker->password(6), // password
            'email' => $this->faker->unique()->safeEmail(),
            'name' => $this->faker->name(),
            'firstname' => $this->faker->firstName(),
            'age' => $this->faker->numberBetween(1, 100),


        ];
    }

    public function configure()
    {
        return $this->afterCreating(function (User $user) {
            User::attachNewToken($user, Token::USER_ABILITIES);
        });
    }


}
