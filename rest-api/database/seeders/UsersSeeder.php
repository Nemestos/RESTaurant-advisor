<?php

namespace Database\Seeders;

use App\Models\User;
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
        User::factory()->count(3)->create();
        $admin = User::factory()->create([
                "login" => "admin",
                "password" => bcrypt(env("ADMIN_PASSWORD")),
                "email" => env("ADMIN_EMAIL")
            ]
        );
        //
    }
}
