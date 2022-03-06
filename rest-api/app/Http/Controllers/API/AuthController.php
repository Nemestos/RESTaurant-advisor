<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Http\Resources\UserResource;
use http\Exception\InvalidArgumentException;
use Illuminate\Auth\AuthenticationException;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use App\Models\User;
use Illuminate\Support\Facades\Auth;

/**
 * Gère tout ce qui est relatif au register/login avec la generation d'un jwt
 */
class AuthController extends Controller
{


    /**
     * @throws \Illuminate\Validation\ValidationException
     */
    public function auth(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'login' => 'required|string',
            'password' => 'required|string'
        ]);
        if ($validator->fails()) {
            return response()->json($validator->errors(), 400);
        }
        $validated = $validator->validated();
        if (!Auth::attempt($validated)) {
            throw new AuthenticationException("can't login with this credentials");
        }
        $last_abilities = User::getAbilities(auth()->user());
        $token = User::refreshToken(auth()->user(), $last_abilities)->plainTextToken;
        return response()->json(["token" => $token, "user_id" => auth()->user()->id], 200);

    }

    /**
     * @throws \Illuminate\Validation\ValidationException
     */
    public function register(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'login' => 'required|string|max:255|unique:users',
            'password' => 'required|string|max:255',
            'email' => 'required|string|email|max:255|unique:users',
            'name' => 'required|string',
            'firstname' => 'required|string',
            'age' => 'required|integer|min:1'
        ]);
        if ($validator->fails()) {
            return response()->json(["errors" => $validator->errors()->all()], 400);
        }
        $validated = $validator->validated();

        list($user, $token) = User::createNormalUser($validated);
        return response()->json(["token" => $token->plainTextToken, "user_id" => $user->id], 201);

    }

    /**
     * @throws \Illuminate\Validation\ValidationException
     */
    public function check(Request $request)
    {
        $validator = Validator::make($request->all(), [
            "token" => "required",
            "abilities" => "required|array",
            "abilities.*" => "required|string|distinct"
        ]);
        if ($validator->fails()) {
            return response()->json($validator->errors(), 400);
        }
        $validated = $validator->validated();
        $token = User::getTokenModel($validated["token"]);
        if ($token == null) {
            return response()->json(["message" => "token expired"], 400);
        }
        foreach ($validated["abilities"] as $ability) {
            if ($token->cant($ability)) {
                return response(["message" => false]);
            }
        }
        return response(["message" => true]);

    }
}
