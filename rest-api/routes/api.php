<?php

use App\Http\Controllers\API\AuthController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
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

Route::group([
    "middleware" => ["auth:sanctum"],
], function () {
    Route::get("/users", [AuthController::class, "index"])->middleware(["ability:get_users"]);

});

