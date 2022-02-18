<?php

namespace Tests\Feature;

use App\Models\Restaurant;
use App\Models\User;
use App\Token;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Testing\Fluent\AssertableJson;
use Laravel\Sanctum\Sanctum;
use Tests\TestCase;

class RestaurantTest extends TestCase
{
    use RefreshDatabase;
    /**
     *
     * /restaurant
     * @return void
     */
    public function test_if_we_can_create_restaurant_when_admin()
    {
        $restaurant = Restaurant::factory()->makeOne();
        $user = User::factory()->makeOne();
        Sanctum::actingAs($user, Token::ADMIN_ABILITIES);
        $resp = $this->json('POST', 'api/restaurant', $restaurant->getAttributes(), ['Accept' => 'application/json']);
        $resp->assertStatus(201);
        $this->assertDatabaseHas("restaurants", [
            "name" => $restaurant->name
        ]);
    }

    /**
     * /restaurants
     *
     * @return void
     */
    public function test_if_we_can_get_restaurant_when_normal_user()
    {
        $user = User::factory()->create();
        $one = Restaurant::factory()->createOne();
        $two = Restaurant::factory()->createOne();

        Sanctum::actingAs($user, Token::USER_ABILITIES);

        $resp = $this->json("GET", "api/restaurants");
        $resp->assertStatus(200);

        $resp->assertJson(function (AssertableJson $json) use ($one, $two) {
            $json
                ->has('data', 2)
                ->has('data.0', function ($json) use ($one) {
                    return $json->where('id', $one->id)
                        ->where('name', $one->name)
                        ->where('description', $one->description)
                        ->where('grade', strval($one->grade))
                        ->where('localization', $one->localization)
                        ->where('phone_number', $one->phone_number)
                        ->where('website', $one->website)
                        ->where('hours', $one->hours);


                })
                ->has('data.1', function ($json) use ($two) {
                    return $json->where('id', $two->id)
                        ->where('name', $two->name)
                        ->where('description', $two->description)
                        ->where('grade', strval($two->grade))
                        ->where('localization', $two->localization)
                        ->where('phone_number', $two->phone_number)
                        ->where('website', $two->website)
                        ->where('hours', $two->hours);

                });
        });


    }
}
