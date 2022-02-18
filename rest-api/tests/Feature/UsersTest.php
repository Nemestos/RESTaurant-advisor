<?php

namespace Tests\Feature;

use App\Models\User;
use App\Token;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Testing\Fluent\AssertableJson;
use Laravel\Sanctum\Sanctum;
use Tests\TestCase;

class UsersTest extends TestCase
{
    use RefreshDatabase;
    /**
     * /users
     *
     * @return void
     */
    public function test_if_we_cant_get_users_when_normal_user()
    {
        $one = User::factory()->createOne();
        $two = User::factory()->createOne();
        Sanctum::actingAs($one);

        $this->json("GET", "api/users")
            ->assertStatus(400);
    }

    /**
     * /users
     *
     * @return void
     */
    public function test_if_we_can_get_users_when_admin()
    {
        $one = User::factory()->createOne();
        $two = User::factory()->createOne();
        Sanctum::actingAs($one, Token::ADMIN_ABILITIES);

        $resp = $this->json("GET", "api/users");
        $resp->assertStatus(200);
        $resp->assertJson(function (AssertableJson $json) use ($two, $one) {
            $json
                ->has('data', 2)
                ->has('data.0', function ($json) use ($one) {
                    return $json->where('id', $one->id)
                        ->where('username', $one->login)
                        ->where('firstname', $one->firstname)
                        ->where('name', $one->name)
                        ->where('age', $one->age);

                })
                ->has('data.1', function ($json) use ($two) {
                    return $json->where('id', $two->id)
                        ->where('username', $two->login)
                        ->where('firstname', $two->firstname)
                        ->where('name', $two->name)
                        ->where('age', $two->age);

                });
        });


    }
}
