<?php

namespace App\Models;


use App\Token;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Support\Facades\DB;
use Laravel\Sanctum\HasApiTokens;
use Illuminate\Foundation\Auth\User as Authenticatable;

class User extends Authenticatable
{
    use HasFactory, HasApiTokens;

    protected $primaryKey = "id";
    protected $fillable = [
        'login',
        'email',
        'name',
        "password",
        'firstname',
        'age',
    ];

    public static function createNormalUser(?array $infos): array
    {

        $infos["password"] = bcrypt($infos["password"]);
        $user = User::create($infos);
        $token = User::attachNewToken($user, Token::USER_ABILITIES);
        return array($user, $token);
    }

    public static function getAbilities(User $user): array
    {
        $row = DB::table("personal_access_tokens")
            ->where("tokenable_id", "=", $user->id)
            ->get("abilities")
            ->collect();
        return json_decode($row[0]->abilities);

    }

    public static function refreshToken(User $user, array $abilities): \Laravel\Sanctum\NewAccessToken
    {
        $user->tokens()->delete();
        return User::attachNewToken($user, $abilities);

    }

    public static function attachNewToken(User $user, array $abilities): \Laravel\Sanctum\NewAccessToken
    {
        return $user->createToken("auth_token", $abilities);
    }

    public static function formNewToken($token): \Illuminate\Http\JsonResponse
    {
        return response()->json([
            'token' => $token,
        ]);
    }


}
