<?php

namespace Tests\Feature;

use App\Http\Resources\UserResource;
use App\Models\User;
use Illuminate\Foundation\Testing\DatabaseMigrations;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Testing\Fluent\AssertableJson;
use Tests\TestCase;

class AuthTest extends TestCase
{
    use DatabaseMigrations;

    /**
     *
     * /register
     * @return void
     */
    public function test_if_we_can_register()
    {
        $user = User::factory()->makeOne();

        $this->json('POST', 'api/register', $user->toArray(), ['Accept' => 'application/json'])
            ->assertStatus(201);
        $this->assertModelExists($user);
    }

    /**
     *
     * /register
     * @return void
     * @depends test_if_we_can_register
     */
    public function test_if_we_cant_register_with_same_login()
    {
        $user = User::factory()->makeOne()->toArray();

        $this->json('POST', 'api/register', $user, ['Accept' => 'application/json'])
            ->assertStatus(201);
        $second = User::factory()->makeOne(["login" => $user["login"]])->toArray();

        $this->json('POST', 'api/register', $user, ['Accept' => 'application/json'])
            ->assertStatus(400);
    }

    /**
     *
     * /register
     * @return void
     * @depends test_if_we_can_register
     */
    public function test_if_we_can_register_with_same_email()
    {
        $user = User::factory()->makeOne()->toArray();

        $this->json('POST', 'api/register', $user, ['Accept' => 'application/json'])
            ->assertStatus(201);
        $second = User::factory()->makeOne(["email" => $user["email"]])->toArray();
        $this->json('POST', 'api/register', $second, ['Accept' => 'application/json'])
            ->assertStatus(400);
    }

    /**
     *
     * /register
     * @return void
     *
     */
    public function test_if_we_cant_register_with_one_missed_field()
    {
        $user = User::factory()->makeOne()->toArray();
        unset($user["password"]);

        $this->json('POST', 'api/register', $user, ['Accept' => 'application/json'])
            ->assertStatus(400);

    }

    /**
     *
     * /register
     * @return void
     *
     */
    public function test_if_we_cant_register_with_many_missed_fields()
    {
        $user = User::factory()->makeOne()->toArray();
        unset($user["password"]);
        unset($user["login"]);


        $this->json('POST', 'api/register', $user, ['Accept' => 'application/json'])
            ->assertStatus(400);
    }

    /**
     * /auth
     * @depends test_if_we_can_register
     * @return void
     */
    public function test_if_we_can_auth_with_one_registered_user()
    {
        $user = User::factory()->makeOne();
        $this->json('POST', 'api/register', $user->toArray(), ['Accept' => 'application/json'])
            ->assertStatus(201);
        $this->json('POST', 'api/auth', [
            "login" => $user["login"],
            "password" => $user["password"]

        ])->assertStatus(200)
            ->assertJsonStructure(['access_token', 'token_type', 'expires_in', 'user']);
    }

    /**
     * /auth
     * @depends test_if_we_can_register
     * @return void
     */
    public function test_if_we_cant_auth_with_incorrect_credentials()
    {
        $user = User::factory()->makeOne();
        $this->json('POST', 'api/register', $user->toArray(), ['Accept' => 'application/json'])
            ->assertStatus(201);
        $this->json('POST', 'api/auth', [
            "login" => $user["login"],
            "password" => "b" . $user["password"] . "a"

        ])->assertStatus(400);
    }

    /**
     * /users
     * @depends test_if_we_can_auth_with_one_registered_user
     * @return void
     */
    public function test_if_we_can_get_users_when_auth()
    {
        $one = User::factory()->createOne();
        $two = User::factory()->createOne();
        $collection = [UserResource::make($one)->toJson(), UserResource::make($two)->toJson()];
//        dd($collection);

        $j = $this->actingAs($one,'api')->json('GET', 'api/users')
            ->assertStatus(200)
//            ->assertJson(fn(AssertableJson $json) =>
//                $json->has(2)
//
        ;
        dd($j);
    }


}
