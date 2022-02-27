<?php

namespace Database\Seeders;

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
        User::factory()->count(3)->create();
        $admin = User::factory()->create([
                "login" => "admin",
                "password" => bcrypt(env("ADMIN_PASSWORD")),
                "email" => env("ADMIN_EMAIL")
            ]
        );
        User::refreshToken($admin, Token::ADMIN_ABILITIES);
//        User::attachNewToken($admin, Token::ADMIN_ABILITIES);
        //
    }
}
