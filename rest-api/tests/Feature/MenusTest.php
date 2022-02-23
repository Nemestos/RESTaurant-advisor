<?php

namespace Tests\Feature;

use App\Models\Menu;
use App\Models\Restaurant;
use App\Models\User;
use App\Token;
use Illuminate\Foundation\Testing\RefreshDatabase;
use Illuminate\Foundation\Testing\WithFaker;
use Illuminate\Support\Facades\Auth;
use Laravel\Sanctum\Sanctum;
use Tests\TestCase;

class MenusTest extends TestCase
{
    use RefreshDatabase;

    /**
     * /restaurant/{id}/menu
     * @return void
     */
    public function test_if_we_can_add_menu_to_restaurant()
    {
        $restaurant = Restaurant::factory()->createOne();
        $user = User::factory()->createOne();
        $menu = Menu::factory()->makeOne();
        $uri = "api/restaurant/" . strval($restaurant->id) . "/menu";
        Sanctum::actingAs($user, Token::ADMIN_ABILITIES);
        $resp = $this->json("POST", $uri, $menu->getAttributes());
        $resp->assertStatus(201);
        $this->assertDatabaseHas("menus", [
            "name" => $menu->name,
            "restaurant_id" => $restaurant->id
        ]);
        $this->assertTrue($restaurant->menus()->exists());
    }

    /**
     * /restaurant/{id}/menus
     * @depends test_if_we_can_add_menu_to_restaurant
     * @return void
     */
    public function test_if_we_can_get_all_menus_from_restaurant()
    {
        $restaurant = Restaurant::factory()->createOne();
        $user = User::factory()->create();
        $menus = Menu::factory()
            ->count(2)
            ->for($restaurant)
            ->create();
        $uri = "api/restaurant/" . strval($restaurant->id) . "/menus";
        Sanctum::actingAs($user, Token::USER_ABILITIES);
        $resp = $this->json("GET", $uri, ['Accept' => 'application/json']);
        $resp->assertStatus(200);

        $resp->assertJsonStructure([
            "data" => [
                "*" => [
                    "name",
                    "restaurant",
                    "description",
                    "price"
                ]
            ]
        ]);

    }

    /**
     * /restaurant/{id}/menu/{id}
     * @depends test_if_we_can_add_menu_to_restaurant
     * @return void
     */
    public function test_if_we_can_modify_one_menu()
    {
        $restaurant = Restaurant::factory()->createOne();
        $user = User::factory()->create();
        $menus = Menu::factory()
            ->count(2)
            ->for($restaurant)
            ->create();
        Sanctum::actingAs($user, Token::ADMIN_ABILITIES);
        $uri = "api/restaurant/" . strval($restaurant->id) . "/menu/" . strval($menus[0]->id);
        $resp = $this->json("PUT", $uri, [
            "name" => "Menu du rat",
            "description" => "un menu pour les rats(comme nino)"
        ]);
        $resp->assertStatus(200);
        $this->assertDatabaseHas("menus", [
            "id" => $menus[0]->id,
            "name" => "Menu du rat",
            "description" => "un menu pour les rats(comme nino)",
        ]);

    }

    /**
     * /restaurant/{id}/menu/{id}
     * @depends test_if_we_can_add_menu_to_restaurant
     * @return void
     */
    public function test_if_we_can_delete_one_menu_to_restaurant()
    {
        $restaurant = Restaurant::factory()->createOne();
        $menus = Menu::factory()
            ->count(1)
            ->for($restaurant)
            ->createOne();

        $user = User::factory()->createOne();
        Sanctum::actingAs($user, Token::ADMIN_ABILITIES);
        $uri = "api/restaurant/" . strval($restaurant->id) . "/menu/" . strval($menus->id);

        $resp = $this->json('DELETE', $uri);
        $resp->assertStatus(200);
        $this->assertDatabaseMissing("menus", [
            "id" => $menus->id
        ]);
    }

    public function test_if_menus_are_deleted_when_delete_the_associated_restaurant()
    {
        $restaurant = Restaurant::factory()->createOne();
        $menus = Menu::factory()
            ->count(3)
            ->for($restaurant)
            ->create();

        $user = User::factory()->createOne();
        Sanctum::actingAs($user, Token::ADMIN_ABILITIES);
        $uri = "api/restaurant/" . strval($restaurant->id);
        $resp = $this->json("DELETE", $uri);
        $resp->assertStatus(200);
        foreach ($menus as $menu) {
//            dd($menu);
            $this->assertDatabaseMissing("menus", $menu->getAttributes());
        }
    }
}
