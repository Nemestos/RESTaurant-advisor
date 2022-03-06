<?php

use App\Http\Controllers\API\AuthController;
use App\Http\Controllers\API\MenuController;
use App\Http\Controllers\API\RestaurantController;
use App\Http\Controllers\API\ReviewsController;
use App\Http\Controllers\API\UsersController;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::post('/register', [AuthController::class, 'register']);
Route::post('/auth', [AuthController::class, 'auth'])->name("login");
Route::post("/checkrights", [AuthController::class, "check"]);
Route::get("/restaurants", [RestaurantController::class, "index"]);
Route::get("/restaurant/{id}", [RestaurantController::class, "get"])->middleware(["menu-rest"]);
Route::get("/restaurant/{id}/menus", [MenuController::class, "index"])->middleware(["menu-rest"]);
Route::get("/restaurant/{rest_id}/menu/{menu_id}", [MenuController::class, "get"])->middleware(["menu-rest"]);
Route::get("/reviews", [ReviewsController::class, "index"]);
Route::get("/reviews/{rest_id}", [ReviewsController::class, "get"])->middleware(["menu-rest"]);
Route::group([
    "middleware" => ["auth:sanctum"],
], function () {
    Route::get("/users", [UsersController::class, "index"])->middleware(["ability:get_users"]);
    Route::post("/restaurant", [RestaurantController::class, "create"])->middleware(["ability:create_restaurant"]);
    Route::put("/restaurant/{id}", [RestaurantController::class, "update"])->middleware(["ability:put_restaurant", "menu-rest"]);
    Route::delete("/restaurant/{id}", [RestaurantController::class, "delete"])->middleware(["ability:delete_restaurant", "menu-rest"]);

    Route::post("/restaurant/{id}/menu", [MenuController::class, "create"])->middleware(["ability:create_menu", "menu-rest"]);
    Route::put("/restaurant/{rest_id}/menu/{menu_id}", [MenuController::class, "update"])->middleware(["ability:put_menu", "menu-rest"]);
    Route::delete("/restaurant/{rest_id}/menu/{menu_id}", [MenuController::class, "delete"])->middleware(["ability:delete_menu", "menu-rest"]);

});

