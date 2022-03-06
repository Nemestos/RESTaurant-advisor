<?php

namespace Database\Seeders;

use App\Models\Restaurant;
use App\Models\Review;
use App\Models\User;
use App\Token;
use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;

class UsersSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        User::truncate();
        DB::table("personal_access_tokens")->truncate();
        $users = User::factory()->count(3)->create();
        $users->each(function (User $user) {
            $reviews = Review::factory(3)->create();
            $user->reviews()->saveMany($reviews);
            $restaurants = Restaurant::inRandomOrder()->limit(3)->get();
            for ($i = 0; $i < 3; $i++) {
                $restaurants[$i]->reviews()->save($reviews[$i]);
            }

        });

        $admin = User::factory()->create([
                "login" => "admin",
                "password" => bcrypt(config("app.admin_password")),
                "email" => config("app.admin_email")
            ]
        );
        User::refreshToken($admin, Token::ADMIN_ABILITIES);
//        User::attachNewToken($admin, Token::ADMIN_ABILITIES);
        //
    }
}
