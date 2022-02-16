<?php

namespace Tests\Feature;

use App\Models\User;
use Illuminate\Foundation\Testing\DatabaseMigrations;
use Illuminate\Foundation\Testing\WithFaker;
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
        $user = User::factory()->makeOne()->toArray();


        $this->json('POST', 'api/register', $user, ['Accept' => 'application/json'])
            ->assertStatus(201);
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
        $this->json('POST', 'api/register', $user, ['Accept' => 'application/json'])
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


}
